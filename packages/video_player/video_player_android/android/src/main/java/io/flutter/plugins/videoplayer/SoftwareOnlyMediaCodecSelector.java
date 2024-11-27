package io.flutter.plugins.videoplayer;

import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;

import java.util.ArrayList;
import java.util.List;

@UnstableApi
public class SoftwareOnlyMediaCodecSelector implements MediaCodecSelector {

    @Override
    public List<MediaCodecInfo> getDecoderInfos(String mimeType, boolean requiresSecureDecoder, boolean requiresTunnelingDecoder) throws MediaCodecUtil.DecoderQueryException {
        List<MediaCodecInfo> allDecoders = MediaCodecUtil.getDecoderInfos(mimeType, requiresSecureDecoder, requiresTunnelingDecoder);
        List<MediaCodecInfo> softwareDecoders = new ArrayList<>();

        // We can't use the java stream api here because it depends on android api 24
        for (MediaCodecInfo codecInfo : allDecoders) {
            if (!codecInfo.hardwareAccelerated) {
                softwareDecoders.add(codecInfo);
            }
        }
        return softwareDecoders;
    }
}