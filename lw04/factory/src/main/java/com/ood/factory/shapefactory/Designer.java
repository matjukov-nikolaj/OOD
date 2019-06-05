package com.ood.factory.shapefactory;

import java.io.InputStream;

public interface Designer {

    PictureDraft createDraft(InputStream in);

}
