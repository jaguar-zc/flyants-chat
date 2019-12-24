package io.sufeng.common.icon.github;

import com.github.afkbrb.avatar.Avatar;
import io.sufeng.common.icon.IconServiceProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: sufeng
 * @create: 2019-12-24 16:30
 */
public class GitHubIconServiceProvider implements IconServiceProvider<Integer> {
    private static final String PICTRUE_FORMATE_JPG = "JPG";

    @Override
    public void generate(Integer i, OutputStream outputStream) throws IOException {
        BufferedImage bufferedImage = new Avatar().generateAndGetAvatar();
        //将最终结果图片输出到指定文件
        ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, outputStream);
    }
}
