package quyetmtph28802.fpoly.bt4_adrnw.API;

import java.util.List;

import quyetmtph28802.fpoly.bt4_adrnw.DTO.Albums;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumAPI {
    @GET("/photos")
    Call<List<Albums>> getAlbums();
}
