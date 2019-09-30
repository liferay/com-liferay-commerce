import {launcher} from '../utilities/index.es';
import AddOrCreate from './AddOrCreate.es';

export default (...data) => launcher(AddOrCreate, ...data);
