package br.com.audsat.vehicleinsurancews.config.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GenericMapper {
    private final ModelMapper modelMapper;

    public GenericMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T toObject(Object object, Class<T> clazz) {
        return Objects.isNull(object) ? null : this.modelMapper.map(object, clazz);
    }

    public <T> List<T> toList(List<?> list, Class<T> clazz) {
        return !Objects.isNull(list) && !list.isEmpty() ? list.stream()
                .map((obj) -> this.toObject(obj, clazz)).collect(Collectors.toList()) : Collections.emptyList();

    }
}
