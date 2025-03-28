package org.example.dtos.json.placeholder;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class UpdateJsonObjectResponseDto {

    private String userId;
    private String title;
    private String body;
    private Integer id;
}
