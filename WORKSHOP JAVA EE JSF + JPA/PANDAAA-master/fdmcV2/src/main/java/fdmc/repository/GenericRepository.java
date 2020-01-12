package fdmc.repository;

import fdmc.domain.models.binding.CatCreateBindingModel;

public interface GenericRepository<E,K> {
    E CreateCat(E entity);
}
