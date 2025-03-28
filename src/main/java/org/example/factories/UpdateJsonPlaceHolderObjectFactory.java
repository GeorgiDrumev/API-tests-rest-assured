package org.example.factories;

import org.example.dtos.json.placeholder.UpdateJsonPlaceholderRequestDto;

public class UpdateJsonPlaceHolderObjectFactory {

    public UpdateJsonPlaceholderRequestDto UpdateJsonPlaceholderRequestDto(String userId,String body, String title) {
        return UpdateJsonPlaceholderRequestDto.builder()
                .userId(userId)
                .title(title)
                .body(body)
                .build();
    }
}
