package com.github.asufana.dtos;

import lombok.Value;
import lombok.experimental.*;

@Value
@Accessors(fluent = true)
public class RepositoryDto {
    
    private String name;
    
}
