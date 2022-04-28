package ru.her.crazy_task.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActDTO {
    private Boolean answer;

    public static ActDTO makeDefault(boolean answer){
        return builder()
                .answer(answer)
                .build();
    }
}
