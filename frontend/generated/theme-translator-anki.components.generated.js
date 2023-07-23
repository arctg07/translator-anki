import { unsafeCSS, registerStyles } from '@vaadin/vaadin-themable-mixin/register-styles';

import vaadinButtonCss from 'themes/translator-anki/components/vaadin-button.css?inline';
import vaadinPasswordFieldCss from 'themes/translator-anki/components/vaadin-password-field.css?inline';
import vaadinTextAreaCss from 'themes/translator-anki/components/vaadin-text-area.css?inline';
import vaadinTextFieldCss from 'themes/translator-anki/components/vaadin-text-field.css?inline';


if (!document['_vaadintheme_translator-anki_componentCss']) {
  registerStyles(
        'vaadin-button',
        unsafeCSS(vaadinButtonCss.toString())
      );
      registerStyles(
        'vaadin-password-field',
        unsafeCSS(vaadinPasswordFieldCss.toString())
      );
      registerStyles(
        'vaadin-text-area',
        unsafeCSS(vaadinTextAreaCss.toString())
      );
      registerStyles(
        'vaadin-text-field',
        unsafeCSS(vaadinTextFieldCss.toString())
      );
      
  document['_vaadintheme_translator-anki_componentCss'] = true;
}

if (import.meta.hot) {
  import.meta.hot.accept((module) => {
    window.location.reload();
  });
}

