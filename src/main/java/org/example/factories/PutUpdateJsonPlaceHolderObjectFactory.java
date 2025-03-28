package org.example.factories;

import org.example.dtos.json.placeholder.PutUpdateJsonPlaceholderRequestDto;

public class PutUpdateJsonPlaceHolderObjectFactory {

    public PutUpdateJsonPlaceholderRequestDto UpdateJsonPlaceholderRequestDto(String userId, String body, String title) {
        return PutUpdateJsonPlaceholderRequestDto.builder()
                .userId(userId)
                .title(title)
                .body(body)
                .build();
    }
}
