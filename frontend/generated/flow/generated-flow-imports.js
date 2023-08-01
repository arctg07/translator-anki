import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '8e3b0b5b9f0e10727ec4344094c74610f594e292d6069e29a0cb37c91661406a') {
    pending.push(import('./chunks/chunk-8e3b0b5b9f0e10727ec4344094c74610f594e292d6069e29a0cb37c91661406a.js'));
  }
  if (key === '439283be21228e7b20723d0ab3ec936d1555501a3fe076688387b2098cc51672') {
    pending.push(import('./chunks/chunk-439283be21228e7b20723d0ab3ec936d1555501a3fe076688387b2098cc51672.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;