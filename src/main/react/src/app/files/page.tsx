type FileResponse = {
    id: number;
    fileName: string;
    fileContent: Uint8Array;
}

async function getFiles() {
    const res = await fetch('http://localhost:8080/api/v1/encrypted_files/all', { cache: 'no-store' })
    const data = await res.json();
    return data;
}

export default async function AllFiles() {
    const files = await getFiles();
    return (
        <div>
            <h1>All Files</h1>
            <div>
                <ul>
                    {files.map((file: FileResponse, index: number) => (
                        <li key={index}>
                            <h1>File Name: <span>{file.fileName}</span></h1>
                            <p>Content: <span>{file.fileContent}</span></p>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    )
}