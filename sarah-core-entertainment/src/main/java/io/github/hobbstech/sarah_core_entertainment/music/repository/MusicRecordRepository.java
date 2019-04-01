package io.github.hobbstech.sarah_core_entertainment.music.repository;

import io.github.hobbstech.sarah_core_entertainment.music.model.Genre;
import io.github.hobbstech.sarah_core_entertainment.music.model.MusicRecord;
import io.github.hobbstech.sarah_core_utils.repository.BaseRepository;

import java.util.Collection;

public interface MusicRecordRepository extends BaseRepository<MusicRecord> {

    Collection<MusicRecord> findAllByGenre(Genre genre);

}
