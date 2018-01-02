package com.sandao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/** 
* Base Tester. 
* 
* @author <Authors name> 
* @since <pre>十一月 17, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@ContextConfiguration({ "classpath:spring/spring-*.xml"})
public class BaseTest {

}
