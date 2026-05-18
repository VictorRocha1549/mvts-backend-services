/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
/**
 *
 * @author vagui
 */
@Configuration
@EnableMongoRepositories(basePackages = "mx.itson.mvts.mvtsbackend.repository.mongo")
public class MongoConfig {
    // Le dice a Spring: Todo lo que esté en la carpeta "mongo" va para Atlas.
}
