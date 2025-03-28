package org.example.dtos.json.placeholder;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class PostJsonPlaceholderRequestDto {

    private String userId;
    private String id;
    private String title;
    private String body;
}
