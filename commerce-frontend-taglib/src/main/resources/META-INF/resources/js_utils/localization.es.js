export const translate = (stringToBeLocalized) => {
	try {
		const localizedString = Liferay.Language.get(stringToBeLocalized);
		if(!localizedString) {
			throw new Error(`Localization not found for string: ${stringToBeLocalized}`)
		}
		return localizedString;
	} catch (error) {
		return stringToBeLocalized
	}
}