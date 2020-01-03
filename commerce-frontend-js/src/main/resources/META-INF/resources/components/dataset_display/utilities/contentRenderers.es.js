import Table from '../content_renderers/table/Table.es'
import EmailsList from '../content_renderers/emails_list/EmailsList';

export const defaultContentRenderers = [
    {
        component: Table,
        default: true,
        icon: 'table',
        id: 'table',
        label: Liferay.Language.get('table'),
    },
    {
        component: EmailsList,
        icon: 'email',
        id: 'emails-list',
        label: Liferay.Language.get('emails-list'),
    }
]

export function getRenderers(newRenderers = []) {
    const newDefaultRenderer = newRenderers.find(renderer => renderer.default);

    return [
        ...(
            newDefaultRenderer 
            ? defaultContentRenderers.map(render => ({...render, default: false})) 
            : defaultContentRenderers
        ),
        ...newRenderers
    ]
}