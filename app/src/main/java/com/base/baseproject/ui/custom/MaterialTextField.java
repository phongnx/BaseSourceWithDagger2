package com.base.baseproject.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.baseproject.R;


/**
 * Created by Phong on 10/21/2016.
 */

public class MaterialTextField extends FrameLayout {
    protected Context context;
    protected TextView label;
    protected View card;
    protected ImageView image;
    protected EditText editText;
    protected ViewGroup editTextLayout;

    protected boolean expanded = false;

    protected int ANIMATION_DURATION = -1;
    protected boolean OPEN_KEYBOARD_ON_FOCUS = true;
    protected int labelColor = -1;
    protected int imageDrawableId = -1;
    protected boolean hasFocus = false;

    protected int cardHeight = -1;

    public MaterialTextField(Context context) {
        super(context);
        this.context = context;
    }

    public MaterialTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        handleAttributes(context, attrs);
    }

    public MaterialTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        handleAttributes(context, attrs);
    }

    public void toggle() {
        if (expanded) {
            reduce();
        } else {
            expand();
        }
    }

    public void reduce() {
        if (expanded) {
            ViewCompat.animate(label)
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1)
                    .translationY(10)
                    .setDuration(ANIMATION_DURATION);

            ViewCompat.animate(image)
                    .alpha(0)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(ANIMATION_DURATION);

            ViewCompat.animate(editText)
                    .alpha(0f)
                    .setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(View view) {
                            card.getLayoutParams().height = cardHeight;
                            card.requestLayout();
                        }
                    })
                    .setDuration(ANIMATION_DURATION);

            ViewCompat.animate(card)
                    .scaleY(0f)
                    .setDuration(ANIMATION_DURATION);

            editText.clearFocus();
            expanded = false;
        }
    }

    public void expand() {
        if (!expanded) {
            ViewCompat.animate(editText)
                    .alpha(1f)
                    .setDuration(ANIMATION_DURATION);

            ViewCompat.animate(card)
                    .scaleY(1f)
                    .setDuration(ANIMATION_DURATION);

            ViewCompat.animate(label)
                    .alpha(0.8f)
                    .scaleX(0.7f)
                    .scaleY(0.7f)
                    .translationY(0)
                    .setDuration(ANIMATION_DURATION);

            ViewCompat.animate(image)
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(ANIMATION_DURATION);

            if (editText != null) {
                editText.requestFocus();
            }
            MarginLayoutParams params = (MarginLayoutParams) label.getLayoutParams();
            params.topMargin = 0;
            label.requestLayout();
            expanded = true;
        }
    }

    public View getCard() {
        return card;
    }

    public TextView getLabel() {
        return label;
    }

    public ImageView getImage() {
        return image;
    }

    public EditText getEditText() {
        return editText;
    }

    public ViewGroup getEditTextLayout() {
        return editTextLayout;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setHasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            expand();
            editText.requestFocusFromTouch();
        } else {
            reduce();
        }
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        if (focused != null) {
            setHasFocus(true);
        } else {
            setHasFocus(false);
        }
    }

    protected void handleAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.MaterialTextField);
            {
                ANIMATION_DURATION = styledAttrs.getInteger(R.styleable.MaterialTextField_mtf_animationDuration, 300);
            }
            {
                OPEN_KEYBOARD_ON_FOCUS = styledAttrs.getBoolean(R.styleable.MaterialTextField_mtf_openKeyboardOnFocus, false);
            }
            {
                labelColor = styledAttrs.getColor(R.styleable.MaterialTextField_mtf_labelColor, -1);
            }
            {
                imageDrawableId = styledAttrs.getResourceId(R.styleable.MaterialTextField_mtf_image, -1);
            }
            {
                cardHeight = styledAttrs.getDimensionPixelOffset(R.styleable.MaterialTextField_mtf_cardHeight, context.getResources().getDimensionPixelOffset(R.dimen.mtf_cardHeight_final));
            }
            {
                hasFocus = styledAttrs.getBoolean(R.styleable.MaterialTextField_mtf_hasFocus, false);
            }

            styledAttrs.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected EditText findEditTextChild() {
        if (getChildCount() > 0 && getChildAt(0) instanceof EditText) {
            return (EditText) getChildAt(0);
        }
        return null;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        editText = findEditTextChild();
        if (editText == null) {
            return;
        }

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_material_text_field, this, false);
        addView(rootView);

        editTextLayout = (ViewGroup) findViewById(R.id.mtf_editTextLayout);
        removeView(editText);
        editTextLayout.addView(editText);

        label = (TextView) findViewById(R.id.mtf_label);
        ViewCompat.setPivotX(label, 0);
        ViewCompat.setPivotY(label, 0);

        if (editText.getHint() != null) {
            label.setText(editText.getHint());
            editText.setHint("");
        }

        card = findViewById(R.id.mtf_card);

        ViewCompat.setScaleY(card, 0);
        ViewCompat.setPivotY(card, 0);

        image = (ImageView) findViewById(R.id.mtf_image);
        ViewCompat.setAlpha(image, 0);
        ViewCompat.setScaleX(image, 0f);
        ViewCompat.setScaleY(image, 0f);
        ViewCompat.setAlpha(editText, 0f);
        editText.setBackgroundColor(Color.TRANSPARENT);

        customizeFromAttributes();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!expanded) {
                    toggle();
                }
            }
        });

        if (!editText.getText().toString().trim().isEmpty()) {
            hasFocus = true;
            expanded = false;
        } else {
            hasFocus = false;
            expanded = true;
            label.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        setEditTextFocusListener(editText);
        setHasFocus(hasFocus);
    }

    protected void setEditTextFocusListener(final EditText editText) {
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (editText.getText().toString().trim().isEmpty()) {
                        expanded = true;
                        setHasFocus(hasFocus);
                        label.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    } else {
                        label.setTextColor(context.getResources().getColor(R.color.dark_gray));
                    }
                } else {
                    if (label != null) {
                        label.setTextColor(context.getResources().getColor(R.color.dark_gray));
                    }
                }
            }
        });
    }

    protected void customizeFromAttributes() {
        if (labelColor != -1) {
            this.label.setTextColor(labelColor);
        }
        if (imageDrawableId != -1) {
            this.image.setImageDrawable(getContext().getResources().getDrawable(imageDrawableId));
        }
    }
}
