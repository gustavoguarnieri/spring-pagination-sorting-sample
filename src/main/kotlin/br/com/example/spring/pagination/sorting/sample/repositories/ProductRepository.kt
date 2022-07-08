package br.com.example.spring.pagination.sorting.sample.repositories

import br.com.example.spring.pagination.sorting.sample.model.entities.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.math.BigInteger

interface ProductRepository : PagingAndSortingRepository<ProductEntity, String> {
    override fun findAll(pageable: Pageable): Page<ProductEntity>
    fun findById(id: BigInteger): ProductEntity?
    fun findByNameLikeIgnoreCase(name: String, pageable: Pageable): Page<ProductEntity>
}
