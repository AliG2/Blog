package modules.users.modules;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category registerCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.getOne(id);
    }

    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
