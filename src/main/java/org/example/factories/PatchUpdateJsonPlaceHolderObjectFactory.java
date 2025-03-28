package org.example.factories;

import org.example.dtos.json.placeholder.PatchUpdateJsonPlaceholderRequestDto;

public class PatchUpdateJsonPlaceHolderObjectFactory {

    public PatchUpdateJsonPlaceholderRequestDto patchUpdateJsonPlaceholderRequestDto(String title) {
        return PatchUpdateJsonPlaceholderRequestDto.builder()
                .title(title)
                .build();
    }
}
