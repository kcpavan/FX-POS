/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.modal.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author kcpavan
 */

@Configuration
@ImportResource("classpath:applicationContext-security.xml")
public class AppSpringConfiguration {
  
    @Bean
    public Item createItem(){
    return new Item();
    }
}
