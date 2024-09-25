package mo.bitcode.kyrie.common.repo;

public interface BasicRepo<T, IdType> {

  T get(IdType id);
  T create(T t);
  T update(T t);
  void remove(T t);

}
