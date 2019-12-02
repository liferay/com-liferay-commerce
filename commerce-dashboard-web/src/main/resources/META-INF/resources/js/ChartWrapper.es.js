import React, { useRef, useCallback } from 'react';
import ResizeObserver from 'resize-observer-polyfill';
import ClayChart from '@clayui/charts';

export default function ChartWrapper({ data, loading }) {
  const chart = useRef();

  const resize = useCallback(
    () => chart.current && chart.current.resize(),
    [chart],
  );

  const wrapper = useCallback(node => {
    if (node !== null) {
      new ResizeObserver(resize).observe(node);
    }
  }, []);

  if (loading) {
    return <span aria-hidden="true" class="loading-animation" />;
  } else if (!data.data.columns.length) {
    return <p>{Liferay.Language.get('no-data-available')}</p>;
  } else {
    return <div ref={wrapper}>
      <ClayChart {...data} ref={chart} />
    </div>;
  }
}
