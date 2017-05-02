package com.example.ivan.easyreader.View.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.easyreader.Model.Translation.Translation;
import com.example.ivan.easyreader.R;

import static android.provider.UserDictionary.Words.WORD;
import static android.provider.VoicemailContract.Voicemails.TRANSCRIPTION;
import static com.example.ivan.easyreader.Presenter.Constants.ARGUMENT_PAGE_NUMBER;
import static com.example.ivan.easyreader.Presenter.Constants.TRANSLATIONS;

/**
 * Created by Myryk on 03.05.2017.
 */
public class TranslationBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        ViewGroup contentView = (ViewGroup) View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);
        TextView transcription = new TextView(getContext());
        transcription.setText(getArguments().getString(TRANSCRIPTION));
        transcription.setTextColor(0xAA000000);
        transcription.setTextSize(20);
        contentView.addView(transcription, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        for (String translation : getArguments().getStringArrayList(TRANSLATIONS)) {
            TextView word = new TextView(getContext());
            word.setText(translation);
            word.setTextColor(0xAA000000);
            word.setTextSize(20);
            contentView.addView(word, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        dialog.setTitle("TTTTIIIITTTLEEEEEEEEE");
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }
}
