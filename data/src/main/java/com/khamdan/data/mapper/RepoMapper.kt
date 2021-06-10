package com.khamdan.data.mapper

import com.khamdan.data.net.dto.AddressDTO
import com.khamdan.data.net.dto.AlbumDTO
import com.khamdan.data.net.dto.CommentDTO
import com.khamdan.data.net.dto.CompanyDTO
import com.khamdan.data.net.dto.GeoDTO
import com.khamdan.data.net.dto.PhotoDTO
import com.khamdan.data.net.dto.PostDTO
import com.khamdan.data.net.dto.UserDTO
import com.khamdan.domain.model.Address
import com.khamdan.domain.model.Album
import com.khamdan.domain.model.Comment
import com.khamdan.domain.model.Company
import com.khamdan.domain.model.Geo
import com.khamdan.domain.model.Photo
import com.khamdan.domain.model.Post
import com.khamdan.domain.model.User
import javax.inject.Inject

class RepoMapper
@Inject constructor() {

    fun transform(modelCollection: List<PostDTO>): List<Post> =
        modelCollection.map { transform(it) }
    //endregion

    fun transform(dto: PostDTO): Post = dto.run { Post(body, id, title, userId) }

    fun transform(dto: UserDTO): User = dto.run {
        User(
            transform(address),
            transform(this.company),
            email,
            id,
            name,
            phone,
            username,
            website
        )
    }

    fun transform(dto: CompanyDTO): Company = dto.run {
        Company(
            bs,
            catchPhrase,
            name
        )
    }

    fun transform(dto: AddressDTO): Address = dto.run {
        Address(
            city,
            transform(geo),
            street,
            suite,
            zipcode
        )
    }

    fun transform(dto: GeoDTO): Geo = dto.run { Geo(lat, lng) }

    fun transform(dto: CommentDTO): Comment = dto.run {
        Comment(
            body,
            email,
            id,
            name,
            postId
        )
    }

    fun transform(dto: AlbumDTO): Album = dto.run {
        Album(id, title, userId)
    }

    fun transform(dto: PhotoDTO): Photo = dto.run {
        Photo(albumId, id, thumbnailUrl, title, url)
    }
}