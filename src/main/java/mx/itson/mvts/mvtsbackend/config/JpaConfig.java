/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 *
 * @author vagui
 */
@Configuration
@EnableJpaRepositories(basePackages = "mx.itson.mvts.mvtsbackend.repository.sql")
public class JpaConfig {
    // Le dice a Spring: Todo lo que esté en la carpeta "sql" va para MySQL local.
}
