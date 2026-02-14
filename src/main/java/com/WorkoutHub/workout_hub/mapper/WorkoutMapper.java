package com.WorkoutHub.workout_hub.mapper;
import com.WorkoutHub.workout_hub.dto.request.SetDto;
import com.WorkoutHub.workout_hub.dto.response.SetLookupEntryDto;
import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Set;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    @BeanMapping(builder = @Builder(disableBuilder = true))
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "exercise", ignore = true)
    @Mapping(target = "performedAt", ignore = true)
    Set toEntity(SetDto setDto);



    @BeanMapping(builder = @Builder(disableBuilder = true))
    @Mapping(target = "position", source = "setNumber")
    SetLookupEntryDto toSetLookupEntry(Set set);

    List<SetLookupEntryDto> toSetLookupEntryList(List<Set> sets);
}
