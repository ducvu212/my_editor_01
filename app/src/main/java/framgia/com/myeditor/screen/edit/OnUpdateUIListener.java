package framgia.com.myeditor.screen.edit;

/**
 * Created by CuD HniM on 18/10/10.
 */
public interface OnUpdateUIListener {
    void updateContrast(int progress);

    void updateBrightness(int progress);

    void OnDoneClick(String type, String name);

    void OnDrawClick();

    void OnChangeColor(int color);

    void OnUndo();

    void OnRedo();

    void OnClear();

    void OnDrawComplete();

    void OnCrop(boolean isCrop);

    void OnStickerItemClick(ItemSticker itemSticker);

    void OnStickerDoneClick();

    void OnStickerClearClick();
}
