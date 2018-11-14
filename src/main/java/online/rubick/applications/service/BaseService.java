package online.rubick.applications.service;

import java.util.List;
public interface BaseService<T> {
    public int save(T entity);// 保存

    public int delete(String id);// 删除

    public int update(T entity);// 更新

    public T findById(String id);// 根据主键查找

    public List<T> getAll();// 查看所有

}   
