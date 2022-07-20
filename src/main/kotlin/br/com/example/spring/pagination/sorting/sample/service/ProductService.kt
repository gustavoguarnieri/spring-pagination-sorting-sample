package br.com.example.spring.pagination.sorting.sample.service

import br.com.example.spring.pagination.sorting.sample.constants.ErrorMessages.Companion.PRODUCT_NOT_FOUND
import br.com.example.spring.pagination.sorting.sample.exceptions.NotFoundException
import br.com.example.spring.pagination.sorting.sample.model.entities.ProductEntity
import br.com.example.spring.pagination.sorting.sample.model.request.ProductPriceUpdateRequest
import br.com.example.spring.pagination.sorting.sample.model.request.ProductRequest
import br.com.example.spring.pagination.sorting.sample.model.response.ProductResponse
import br.com.example.spring.pagination.sorting.sample.repositories.ProductRepository
import mu.KotlinLogging
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class ProductService(
    private val repository: ProductRepository,
    private val modelMapper: ModelMapper
) {

    private val log = KotlinLogging.logger {}

    fun createProduct(productRequest: ProductRequest): ProductResponse {
        val productEntity = convertToEntity(productRequest)
        val productResponse = repository.save(productEntity)
        return convertToDto(productResponse)
    }

    fun updateProduct(id: BigInteger, productRequest: ProductRequest) {
        repository.findById(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND).also {
            log.warn { "updateProduct: product not found, productId=$id" }
        }

        val productEntity = convertToEntity(productRequest)
        productEntity.id = id

        repository.save(productEntity)
    }

    fun updateProductPrice(id: BigInteger, productPriceUpdateRequest: ProductPriceUpdateRequest) {
        val productEntity = repository.findById(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND).also {
            log.warn { "updateProduct: product not found, productId=$id" }
        }
        productEntity.price = productPriceUpdateRequest.price
        repository.save(productEntity)
    }

    fun getProduct(id: BigInteger): ProductResponse {
        val product = repository.findById(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND).also {
            log.warn { "getProduct: product not found, productId=$id" }
        }
        return convertToDto(product)
    }

    fun getProducts(searchTerm: String?, sortBy: String?, page: Int, size: Int): Page<ProductResponse> {
        val pageRequest = sortBy?.let {
            PageRequest.of(page, size, Sort.by(sortBy))
        } ?: run {
            PageRequest.of(page, size)
        }

        return searchTerm?.let { searchTermNotNull ->
            repository.findByNameLikeIgnoreCase(searchTermNotNull, pageRequest).map { convertToDto(it) }
        } ?: run {
            repository.findAll(pageRequest).map { convertToDto(it) }
        }
    }

    private fun convertToDto(productEntity: ProductEntity): ProductResponse {
        return modelMapper.map(productEntity, ProductResponse::class.java)
    }

    private fun convertToEntity(productRequest: ProductRequest): ProductEntity {
        return modelMapper.map(productRequest, ProductEntity::class.java)
    }
}
