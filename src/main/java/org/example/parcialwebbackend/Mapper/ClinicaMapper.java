package org.example.parcialwebbackend.Mapper;

import org.example.parcialwebbackend.Dtos.ClinicaDto;
import org.example.parcialwebbackend.Model.Clinica;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClinicaMapper {

    private final ModelMapper modelMapper;

    public ClinicaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClinicaDto toDto(Clinica clinica) {
        return modelMapper.map(clinica, ClinicaDto.class);
    }

    public Clinica toEntity(ClinicaDto clinicaDto) {
        return modelMapper.map(clinicaDto, Clinica.class);
    }
}