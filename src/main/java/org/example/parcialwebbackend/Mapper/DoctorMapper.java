package org.example.parcialwebbackend.Mapper;

import org.example.parcialwebbackend.Dtos.DoctorDto;
import org.example.parcialwebbackend.Model.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    private final ModelMapper modelMapper;

    public DoctorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DoctorDto toDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDto.class);
    }

    public Doctor toEntity(DoctorDto doctorDto) {
        return modelMapper.map(doctorDto, Doctor.class);
    }
}