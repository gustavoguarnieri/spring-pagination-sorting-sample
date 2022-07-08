package br.com.example.spring.pagination.sorting.sample

import br.com.example.spring.pagination.sorting.sample.constants.Products
import br.com.example.spring.pagination.sorting.sample.repositories.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCachingKotlinSampleApplication(
    private val repository: ProductRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        repository.deleteAll()
        Products.productCatalog.forEach(repository::save)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringCachingKotlinSampleApplication>(*args)
}