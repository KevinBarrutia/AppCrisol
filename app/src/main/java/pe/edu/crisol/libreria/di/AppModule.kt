package pe.edu.crisol.libreria.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.crisol.libreria.data.repository.BookRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

}
