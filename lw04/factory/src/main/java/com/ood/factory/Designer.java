package com.ood.factory;

import java.io.InputStream;

public interface Designer {

    PictureDraft createDraft(InputStream in);

}
