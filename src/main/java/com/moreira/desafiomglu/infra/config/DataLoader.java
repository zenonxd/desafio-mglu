package com.moreira.desafiomglu.infra.config;

import com.moreira.desafiomglu.domain.entities.Channel;
import com.moreira.desafiomglu.domain.entities.Status;
import com.moreira.desafiomglu.domain.repositories.ChannelRepository;
import com.moreira.desafiomglu.domain.repositories.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;

    public DataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        //we access all the values of the enum subclass
        Arrays.stream(Channel.Values.values())
                //we convert the enum to entity
                .map(Channel.Values::toChannel)
                //save everything on database
                .forEach(channelRepository::save);

        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(statusRepository::save);
    }
}
