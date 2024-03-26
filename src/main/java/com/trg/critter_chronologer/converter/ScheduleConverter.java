package com.trg.critter_chronologer.converter;

import com.trg.critter_chronologer.entity.Schedule;
import com.trg.critter_chronologer.payload.dto.ScheduleDTO;

public interface ScheduleConverter {
    ScheduleDTO convertToDTO(Schedule schedule);
    Schedule convertToEntity(ScheduleDTO scheduleDTO);
}
