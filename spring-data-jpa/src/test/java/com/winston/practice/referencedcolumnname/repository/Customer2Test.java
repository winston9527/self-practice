package com.winston.practice.referencedcolumnname.repository;

import com.winston.practice.entity.Customer;
import com.winston.practice.entity.LinkMan;
import com.winston.practice.repository.CustomerRepository;
import com.winston.practice.repository.LinkManRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class Customer2Test {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LinkManRepository linkManRepository;

    @Test
    public void testSave() {

        Customer customer = Customer.builder()
          .name("倪华梅教育")
          .build();
        LinkMan linkMan = LinkMan.builder()
          .name("朱晓明")
          .mobileNumber("15102993849")
          .build();
        customerRepository.save(customer);
        linkManRepository.save(linkMan);
        //成功保存了 两张表  但是外键没有值
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testSave2() {

        Customer customer = Customer.builder()
          .name("倪华梅教育")
          .build();

        LinkMan linkMan = LinkMan.builder()
          .name("朱晓明")
          .mobileNumber("15102993849")
          .build();
        Set<LinkMan> set = new HashSet<>();
        set.add(linkMan);
        customer.setLinkMan(set);

        customerRepository.save(customer);
        linkManRepository.save(linkMan);
        //保存了外键   但是执行的步骤不是最优的
        /**
         * Hibernate: insert into customer (name) values (?)
         * Hibernate: insert into link_man (customer_id, mobile_number, name) values (?, ?, ?)
         * Hibernate: update link_man set customer_id=? where id=?
         */
    }



    @Test
    @Transactional
    @Rollback(false)
    public void testSave3() {

        Customer customer = Customer.builder()
          .name("倪华梅教育")
          .build();

        LinkMan linkMan = LinkMan.builder()
          .name("朱晓明")
          .mobileNumber("15102993849")
          .build();

        linkMan.setCustomer(customer);
        customerRepository.save(customer);
        linkManRepository.save(linkMan);
        //让子表去维护关系  并且先保存主表  此时只有两条SQL
        /**
         * Hibernate: insert into customer (name) values (?)
         * Hibernate: insert into link_man (customer_id, mobile_number, name) values (?, ?, ?)
         */
    }



    @Test
    @Transactional
    @Rollback(false)
    public void testSave4() {

        Customer customer = Customer.builder()
          .name("倪华梅教育")
          .build();

        LinkMan linkMan = LinkMan.builder()
          .name("朱晓明")
          .mobileNumber("15102993849")
          .build();


        linkMan.setCustomer(customer);//由于多的一方维护了外键关系 当保存的时候 就已经有值了

        Set<LinkMan> set = new HashSet<>();
        set.add(linkMan);
        customer.setLinkMan(set);//  由于一的一方配置了到多的一方的维护关系 所以又会发送一条SQL 语句
        /**可以通过修映射关系  将1的一方的放弃维护 外键关系
         * @OneToMany
         * @JoinColumn(name = "customer_id",referencedColumnName ="id" )
         * 修改为
         *@OneToMany(mappedBy = "customer")
         * mappedBy  只设置关系  但是不维护外键
         * 但是这样  就会导致  testSave2 又不能保存外键了
        // */
        customerRepository.save(customer);
        linkManRepository.save(linkMan);
        /**
         * Hibernate: insert into customer (name) values (?)
         * Hibernate: insert into link_man (customer_id, mobile_number, name) values (?, ?, ?)
         * Hibernate: update link_man set customer_id=? where id=?
         */
    }


    @Test
    @Transactional
    @Rollback(false)
    public void testDelete() {

        Optional<Customer> byId = customerRepository.findById(7L);
        customerRepository.delete(byId.orElseGet(null));
        //报错   因为我们在上面的case里面  customer放弃了外键维护权，会导致他不能修改外键的值为null
        //所以删除的时候 会发现这个主键正在被子表的外键引用
        /** 我们将维护外键的权利放回来的时候  又可以删了
         * @OneToMany
         *      @JoinColumn(name = "customer_id",referencedColumnName ="id" )
         *     //@OneToMany(mappedBy = "customer")
         *
         *     执行的SQL为
         * Hibernate: select customer0_.id as id1_2_0_, customer0_.name as name2_2_0_ from customer customer0_ where customer0_.id=?
         * Hibernate: update link_man set customer_id=null where customer_id=?
         * Hibernate: delete from customer where id=?
         */
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test() {

         Customer byId = customerRepository.findById(9L).orElseGet(null);

        LinkMan linkMan = LinkMan.builder()
          .name("朱晓明22")
          .mobileNumber("15102993849")
          .build();

        linkMan.setCustomer(byId);
        byId.getLinkMan().clear();
        byId.getLinkMan().add(linkMan);
        //byId.setName("test 教育集团");

        customerRepository.save(byId);

    }

}
