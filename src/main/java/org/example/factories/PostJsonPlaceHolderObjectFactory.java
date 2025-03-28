package org.example.factories;

import org.example.dtos.json.placeholder.PostJsonPlaceholderRequestDto;

public class PostJsonPlaceHolderObjectFactory {

    public PostJsonPlaceholderRequestDto postJsonPlaceholderRequestDto(String userId, String id,String body, String title) {
        return PostJsonPlaceholderRequestDto.builder()
                .userId(userId)
                .userId(id)
                .title(title)
                .body(body)
                .build();
    }
}
