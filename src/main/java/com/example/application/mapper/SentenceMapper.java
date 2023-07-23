package com.example.application.mapper;


import com.example.application.configuration.MapperConfig;
import com.example.application.database.entity.Sentence;
import com.example.application.model.EngRusDto;
import org.mapstruct.Mapper;

/**
 * @author Iurii Ivanov
 */

@Mapper(config = MapperConfig.class)
public interface SentenceMapper {

    EngRusDto toEngRus(Sentence sentence);
}
