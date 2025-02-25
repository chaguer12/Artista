package project.Artista.dto.mapper.mappers;

import org.mapstruct.Mapper;
import project.Artista.dto.mapper.GenericMapper;
import project.Artista.dto.records.blogPost.BlogReqDTO;
import project.Artista.dto.records.blogPost.BlogResDTO;
import project.Artista.model.BlogPost;
@Mapper(config = GenericMapper.class)
public interface BlogPostMapper extends GenericMapper<BlogPost, BlogReqDTO, BlogResDTO> {

}
