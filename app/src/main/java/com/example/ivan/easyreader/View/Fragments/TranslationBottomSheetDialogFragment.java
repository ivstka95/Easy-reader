package com.example.ivan.easyreader.View.Fragments;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.easyreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.UserDictionary.Words.WORD;
import static android.provider.VoicemailContract.Voicemails.TRANSCRIPTION;
import static com.example.ivan.easyreader.Presenter.Constants.TRANSLATIONS;

/**
 * Created by Myryk on 03.05.2017.
 */
public class TranslationBottomSheetDialogFragment extends BottomSheetDialogFragment {

    @BindView(R.id.tvWord)
    TextView tvWord;
    @BindView(R.id.tvTranscription)
    TextView tvTranscription;
    @BindView(R.id.tvTranslations)
    TextView tvTranslations;
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
        ButterKnife.bind(this, contentView);
        tvWord.setText(getArguments().getString(WORD));
        tvTranscription.setText(getArguments().getString(TRANSCRIPTION));
        String translations = "";
        for (String translation : getArguments().getStringArrayList(TRANSLATIONS))
            translations += translation + ", ";
        if (translations.length() > 2)
            tvTranslations.setText(translations.substring(0, translations.length() - 2));
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }
}
