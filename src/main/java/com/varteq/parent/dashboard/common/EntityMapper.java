package com.varteq.parent.dashboard.common;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityMapper<Entity, Dto> {

    public Dto toDto(Entity entity) {
        if (entity == null) {
            return null;
        }
        return asDto(entity);
    }

    public Entity toEntity(Dto dto) {
        if (dto == null) {
            return null;
        }
        return asEntity(dto);
    }

    protected abstract Dto asDto(Entity entity);

    protected abstract Entity asEntity(Dto dto);

    public List<Entity> toEntityList(List<Dto> dtoList) {
        if (dtoList == null) {
            return Collections.emptyList();
        }
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<Dto> toDtoList(List<Entity> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
