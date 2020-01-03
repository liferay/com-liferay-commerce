import React, { useContext } from 'react'
import ClayList from '@clayui/list';
import ClayLabel from '@clayui/label';
import ClaySticker from '@clayui/sticker';
import ActionsDropdown from '../common/ActionsDropdown.es';
import classNames from 'classnames';
import DatasetContext from '../../DatasetDisplayContext.es';

import PropTypes from 'prop-types';
import { OPEN_SIDE_PANEL } from '../../../../utilities/eventsDefinitions.es';

function Email(props) {

    const {loadData} = useContext(DatasetContext);

    function handleClickOnSubject(e) {
        e.preventDefault();

        Liferay.fire(OPEN_SIDE_PANEL, {
            id: props.sidePanelId,
            onAfterSubmit: () => loadData(),
            slug: 'email',
            url: props.url,
        })
    }

    return (
        <li 
            className={classNames(
                "bg-white d-flex p-4", 
                props.borderBottom 
                ? "border-top-0 border-left-0 border-right-0 border-bottom" 
                : "border-0"
            )}
        >
            <div className="row">
                <div className="col">
                    <div className="row">
                        <div className="col">
                            <div className="row">
                                {props.author.avatarUrl && (
                                    <div className="col-auto">
                                        <ClaySticker className="sticker-user-icon" size="xl">
                                            <div className="sticker-overlay">
                                                <img className="sticker-img" src={props.author.avatarUrl} />
                                            </div>
                                        </ClaySticker>
                                    </div>
                                )}
                                <div className="col d-flex flex-column justify-content-center">
                                    <small className="d-block text-body"><strong>{props.author.name}</strong></small>
                                    <small className="d-block">{props.author.email}</small>
                                </div>
                            </div>
                        </div>
                        <div className="col-auto d-flex flex-column justify-content-center">
                            <ClayLabel displayType={props.status.displayStyle || 'success'}>
                                {props.status.label}
                            </ClayLabel>
                        </div>
                        <div className="col-auto d-flex flex-column justify-content-center">
                            <small>{props.date}</small>
                        </div>
                        <div className="col-12">
                            <h5 className="mt-3"><a href="#" onClick={handleClickOnSubject}>{props.subject}</a></h5>
                            <div>
                                {props.abstract}
                            </div>
                        </div>
                    </div>
                </div>
                {props.actionItems.length ? (
                    <div className="col-auto d-flex flex-column justify-content-center">
                        <ActionsDropdown items={props.actionItems} />
                    </div>
                ) : null}
            </div>
        </li>
    )
}

Email.propTypes = {
    abstract: PropTypes.string.isRequired,
    actionItems: PropTypes.array,
    author: PropTypes.shape({
        avatarUrl: PropTypes.string,
        email: PropTypes.string.isRequired,
        name: PropTypes.string.isRequired
    }).isRequired,
    borderBottom: PropTypes.bool,
    date: PropTypes.string.isRequired,
    sidePanelId: PropTypes.string,
    status: PropTypes.shape({
        displayStyle: PropTypes.string,
        label: PropTypes.string.isRequired
    }),
    subject: PropTypes.string.isRequired,
    url: PropTypes.string,
}

Email.defaultProps = {
    actionItems: []
}

function EmailsList(props) {
    return (
        <ClayList className="mb-0">
            {props.items.map((item, i) => (
                <Email 
                    key={i}
                    {...item}
                    borderBottom={i !== props.items.length - 1}
                    sidePanelId={props.sidePanelId}
                />
            ))}
        </ClayList>
    )
}

EmailsList.propTypes = {
    items: PropTypes.array,
    sidePanelId: PropTypes.string
}

EmailsList.defaultProps = {
    items: []
}

export default EmailsList