package com.berkayarslan.UserService.general;

import com.berkayarslan.UserService.exceptions.ItemNotFoundException;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E,Long>> {

    private final R repository;

    protected BaseEntityService(R repository) {
        this.repository = repository;
    }

    public E save(E entity){
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if(baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
        }

        LocalDateTime now = LocalDateTime.now();
        if(entity.getId() == null){
            baseAdditionalFields.setCreateDate(now);
        }
        baseAdditionalFields.setUpdateDate(now);

        entity.setBaseAdditionalFields(baseAdditionalFields);

        entity = repository.save(entity);

        return entity;
    }

    public List<E> findAll(){
        return repository.findAll();
    }

    public E findByIdWithControl(Long id){
        Optional<E> optionalE = repository.findById(id);
        E entity;

        if(optionalE.isPresent()){
            entity = optionalE.get();
        }else {
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }
        return entity;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
