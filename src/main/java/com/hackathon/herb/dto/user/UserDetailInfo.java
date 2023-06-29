package com.hackathon.herb.dto.user;

import com.hackathon.herb.dto.article.ArticlePreviewInfo;
import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class UserDetailInfo {
    private UserInfo userInfo;
    private List<ArticlePreviewInfo> articlePreviewInfo;

    public static UserDetailInfo of(UserEntity user) {
        UserInfo u = UserInfo.of(user);
        List<ArticlePreviewInfo> ape = new ArrayList<>();

        for (ArticleEntity art : user.getArticles()) {
            ape.add(ArticlePreviewInfo.of(art));
        }

        return new UserDetailInfo(u, ape);
    }
}
