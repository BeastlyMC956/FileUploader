import './App.css';
import GetFiles from './GetFiles';
import UploadFile from './UploadFile';
import Header from './template/NavBar';

function App() {
  return (
    <div className='App'>
      <Header />
      <UploadFile />
      <GetFiles />
    </div>
  );
}

export default App;
