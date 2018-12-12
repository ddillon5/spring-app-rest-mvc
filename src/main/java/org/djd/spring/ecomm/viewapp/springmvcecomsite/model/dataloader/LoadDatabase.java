package org.djd.spring.ecomm.viewapp.springmvcecomsite.model.dataloader;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.Chair;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.Desk;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.repos.ChairRepository;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.repos.DeskRepository;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.ColorEnum;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.SizeEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(ChairRepository chairRepository, DeskRepository deskRepository) {
		return args -> {
			log.info("Preloading " + chairRepository.save(new Chair(SizeEnum.SMALL,ColorEnum.GREEN, 123.99f, "Fabco Chair3000")));
			log.info("Preloading " + chairRepository.save(new Chair(SizeEnum.LARGE,ColorEnum.RED, 499.99f, "Fabco Chair4000")));
			
			log.info("Preloading " + deskRepository.save(new Desk(SizeEnum.MEDIUM,ColorEnum.WHITE, 1066.99f, "Fabco Ergo Desk")));
			log.info("Preloading " + deskRepository.save(new Desk(SizeEnum.SMALL,ColorEnum.BLACK, 9999.99f, "Fabco Ego Desk")));
			
		};
	}
}
