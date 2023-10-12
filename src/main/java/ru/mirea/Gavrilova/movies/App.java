package ru.mirea.Gavrilova.movies;

import com.fasterxml.jackson.databind.json.JsonMapper;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args)
            throws IOException, InterruptedException, SQLException {
        Retrofit client = new Retrofit
                .Builder()
                .baseUrl("https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/")
                .addConverterFactory(JacksonConverterFactory.create(new JsonMapper()))
                .build();

        Service service = client.create(Service.class);

        try {
            Call<List<DTO>> call = service.getDTO();
            Response<List<DTO>> response = call.execute();

            if (response.isSuccessful()) {
                List<DTO> movies = response.body();

                Optional<DTO> result = movies.stream()
                        .filter(movie -> movie.getYear() < 2000)
                        .max((movie1, movie2) -> Integer.compare(movie1.getCast().size(),
                                movie2.getCast().size()));

                if (!result.isEmpty()) {
                    DTO movieMaxActors = result.get();
                    System.out.println("Фильм с самым большим количеством актеров (до 2000го года): " +
                            "кол-во актеров = " + movieMaxActors.getCast().size() + '\n'
                            + movieMaxActors.toString());
                } else {
                    System.out.println("Фильмы не были найдены");
                }
            } else {
                System.out.println("Ошибка при выполнении запроса");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
