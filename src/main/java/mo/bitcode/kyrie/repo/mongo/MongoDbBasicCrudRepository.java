package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.common.repo.BasicRepo;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MongoDbBasicCrudRepository<T> extends BasicRepo<T, ObjectId>, CrudRepository<T, ObjectId> {

  @Override
  default T get(ObjectId id) {
    return findById(id).orElse(null);
  }

  @Override
  default T create(T t) {
    return save(t);
  }

  @Override
  default T update(T t) {
    return save(t);
  }

  @Override
  default void remove(T t) {
    delete(t);
  }

}
